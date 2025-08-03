// ...existing code...
// ─── STATE ───
let PRODUCTS = [];
let userProfile = null;
const cartMap = {}; // id → { product, qty }

// ─── HELPERS ───
const $ = (s) => document.querySelector(s);

// ─── API CALLS ───
async function fetchProfile() {
  const res = await fetch("/api/user/profile");
  userProfile = await res.json();
  updateProfileUI();
}
async function fetchRewards() {
  const res = await fetch("/api/rewards");
  const data = await res.json();
  PRODUCTS = (data.rewards || []).map(r => ({
    id: r.id,
    name: r.name,
    points: r.price,
    img: r.image,
    stock: r.stockCount,
    description: r.description,
    category: r.category,
    backend: r // keep full backend object for later use
  }));
  renderGrid();
}
async function fetchCart() {
  const res = await fetch("/api/cart");
  const data = await res.json();
  // Sync cartMap with backend cart
  Object.keys(cartMap).forEach(id => delete cartMap[id]);
  (data.items || []).forEach(item => {
    cartMap[item.reward.id] = { product: PRODUCTS.find(p => p.id === item.reward.id) || item.reward, qty: item.quantity };
  });
  updateCartCount();
}
async function addToCartBackend(product, qty = 1) {
  await fetch("/api/cart/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ rewardId: product.id, quantity: qty })
  });
  await fetchCart();
}
async function checkoutBackend() {
  const res = await fetch("/api/checkout", { method: "POST" });
  const data = await res.json();
  if (data.success) {
    alert("Checkout finalizat!");
    await fetchProfile();
    await fetchCart();
    renderGrid();
    closeModal("#cart-modal");
  } else {
    alert(data.message || "Checkout failed!");
  }
}

// ─── PROFILE UI ───
function updateProfileUI() {
  if (!userProfile) return;
  $(".user-name").textContent = userProfile.name;
  $(".user-points").textContent = `${userProfile.activityPoints} AP`;
  if (userProfile.avatar) $(".avatar").src = userProfile.avatar;
}

// ─── RENDER GRID ───
function renderGrid() {
  const grid = $("#product-grid");
  const term = $("#search-input").value.toLowerCase();
  const filter = $("#filter-select").value;
  grid.innerHTML = "";

  PRODUCTS.forEach((p) => {
    if (!p.name.toLowerCase().includes(term)) return;
    if (filter && p.category !== filter) return;

    const card = document.createElement("div");
    card.className = `card ${p.stock > 0 ? "in-stock" : "out-of-stock"}`;
    card.innerHTML = `
      <img src="${p.img}" alt="${p.name}" class="product-image">
      <h4>${p.name}</h4>
      <div class="price-line">
        <span class="points">${p.points} AP</span>
        <button class="card-add-btn" ${p.stock > 0 ? "" : "disabled"}>
          <img src="assets/images/backpack-small.png" alt="Adaugă">
        </button>
      </div>
    `;
    card.querySelector(".card-add-btn").onclick = async (e) => {
      e.stopPropagation();
      if (p.stock > 0) {
        await addToCartBackend(p, 1);
      }
    };
    card.addEventListener("click", () => openProductModal(p));
    grid.append(card);
  });
}

// ─── MODAL & CART ───
function openProductModal(p) {
  $("#modal-img").src = p.img;
  $("#modal-name").textContent = p.name;
  $("#modal-desc").textContent = p.description;
  $("#modal-points").textContent = `${p.points} AP`;
  $("#modal-stock").textContent = p.stock > 0 ? "În Stoc" : "Stoc Epuizat";

  const btn = $("#modal-add");
  if (p.stock > 0) {
    btn.classList.remove("hidden");
    btn.onclick = async () => {
      await addToCartBackend(p, 1);
      closeModal("#product-modal");
    };
  } else {
    btn.classList.add("hidden");
  }
  $("#product-modal").classList.remove("hidden");
}
$("#close-product-modal").onclick = () => closeModal("#product-modal");
function closeModal(sel) {
  $(sel).classList.add("hidden");
}

$("#cart-button").onclick = async () => {
  await fetchCart();
  renderCart();
  $("#cart-modal").classList.remove("hidden");
};
$("#close-cart-modal").onclick = () => closeModal("#cart-modal");

function updateCartCount() {
  const total = Object.values(cartMap).reduce((sum, e) => sum + e.qty, 0);
  $("#cart-count").textContent = total;
}

function renderCart() {
  const ul = $("#cart-items");
  ul.innerHTML = "";
  Object.values(cartMap)
    .filter((e) => e.qty > 0)
    .forEach((e) => {
      const { product, qty } = e;
      const li = document.createElement("li");
      li.innerHTML = `
        <span class="cart-item-name">${product.name}</span>
        <div class="cart-item-controls">
          <button class="qty-btn decrease">−</button>
          <span class="qty">${qty}</span>
          <button class="qty-btn increase">+</button>
        </div>
        <span class="cart-item-points">${product.points * qty} AP</span>
      `;
      li.querySelector(".decrease").onclick = async () => {
        if (qty > 1) {
          await addToCartBackend(product, -1);
        } else {
          await addToCartBackend(product, -qty);
        }
        await fetchCart();
        renderCart();
      };
      li.querySelector(".increase").onclick = async () => {
        if (qty < product.stock) {
          await addToCartBackend(product, 1);
          await fetchCart();
          renderCart();
        }
      };
      ul.append(li);
    });

  const totalAP = Object.values(cartMap).reduce(
    (sum, e) => sum + e.product.points * e.qty,
    0
  );
  $("#cart-total").textContent = `Total: ${totalAP} AP`;
  $("#checkout-btn").classList.toggle("hidden", totalAP === 0);
}

$("#checkout-btn").onclick = async () => {
  await checkoutBackend();
};

// ─── INIT ───
document.addEventListener("DOMContentLoaded", async () => {
  await fetchProfile();
  await fetchRewards();
  await fetchCart();
  $("#search-input").addEventListener("input", renderGrid);
  $("#filter-select").addEventListener("change", renderGrid);
});