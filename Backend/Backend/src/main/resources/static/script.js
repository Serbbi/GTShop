// ─── MOCK DATA & STATE ───
const PRODUCTS = [
  {
    id: 1,
    name: "Căști Wireless",
    points: 1000,
    img: "earbuds.png",
    stock: 5,
    description: "Sunet clar HD",
    category: "Audio",
  },
  {
    id: 2,
    name: "Ceas Smart",
    points: 2000,
    img: "watch.png",
    stock: 0,
    description: "Monitorizează-ți sănătatea",
    category: "Lifestyle",
  },
  {
    id: 3,
    name: "Căști Samsung",
    points: 1000,
    img: "earbuds.png",
    stock: 5,
    description: "Sunet clar HD",
    category: "Audio",
  },
  {
    id: 4,
    name: "Ceas Google",
    points: 2000,
    img: "watch.png",
    stock: 0,
    description: "Monitorizează-ți sănătatea",
    category: "Lifestyle",
  },
  {
    id: 5,
    name: "Căști cu fir",
    points: 1000,
    img: "earbuds.png",
    stock: 5,
    description: "Sunet clar HD",
    category: "Audio",
  },
  {
    id: 6,
    name: "Ceas clasic",
    points: 2000,
    img: "watch.png",
    stock: 0,
    description: "Monitorizează-ți sănătatea",
    category: "Lifestyle",
  },
  // … alte premii
];
let cart = [];

// ─── HELPERS ───
const $ = (s) => document.querySelector(s);
const $all = (s) => Array.from(document.querySelectorAll(s));

// ─── RENDER GRID ───
function renderGrid() {
  const grid = $("#product-grid");
  grid.innerHTML = "";
  const search = $("#search-input").value.toLowerCase();
  const filter = $("#filter-select").value;
  PRODUCTS.forEach((p) => {
    if (!p.name.toLowerCase().includes(search)) return;
    if (filter && p.category !== filter) return;
    const card = document.createElement("div");
    card.className = `card ${p.stock > 0 ? "in-stock" : "out-of-stock"}`;
    card.innerHTML = `
      <img src="${p.img}" alt="${p.name}">
      <h4>${p.name}</h4>
      <p>${p.points} AP</p>
      <div class="stock-indicator"></div>
    `;
    card.addEventListener("click", () => openProductModal(p));
    grid.append(card);
  });
}

// // ─── PRODUCT MODAL ───
// function openProductModal(p) {
//   $("#modal-img").src = p.img;
//   $("#modal-name").textContent = p.name;
//   $("#modal-desc").textContent = p.description;
//   $("#modal-points").textContent = `${p.points} AP`;
//   $("#modal-stock").textContent = p.stock > 0 ? "În Stoc" : "Stoc Epuizat";
//   $("#product-modal").classList.remove("hidden");

//   $("#modal-add").onclick = () => {
//     if (p.stock > 0) {
//       cart.push(p);
//       $("#cart-count").textContent = cart.length;
//       alert(`Adăugat ${p.name} în coș`);
//     } else {
//       alert("Stoc epuizat!");
//     }
//     closeProductModal();
//   };
// }

// ─── PRODUCT MODAL (hide add btn if stock=0) ───
function openProductModal(p) {
  $("#modal-img").src = p.img;
  $("#modal-name").textContent = p.name;
  $("#modal-desc").textContent = p.description;
  $("#modal-points").textContent = `${p.points} AP`;
  $("#modal-stock").textContent = p.stock > 0 ? "În Stoc" : "Stoc Epuizat";

  const addBtn = $("#modal-add");
  if (p.stock > 0) {
    addBtn.classList.remove("hidden");
    addBtn.onclick = () => {
      cart.push(p);
      $("#cart-count").textContent = cart.length;
      alert(`Adăugat ${p.name} în coș`);
      closeProductModal();
    };
  } else {
    addBtn.classList.add("hidden");
  }

  $("#product-modal").classList.remove("hidden");
}

$("#close-product-modal").onclick = closeProductModal;
function closeProductModal() {
  $("#product-modal").classList.add("hidden");
}

// ─── CART MODAL ───
$("#cart-button").onclick = () => {
  renderCart();
  $("#cart-modal").classList.remove("hidden");
};
$("#close-cart-modal").onclick = () => {
  $("#cart-modal").classList.add("hidden");
};

// function renderCart() {
//   const ul = $("#cart-items");
//   ul.innerHTML = "";
//   cart.forEach((p) => {
//     const li = document.createElement("li");
//     li.textContent = `${p.name} – ${p.points} AP`;
//     ul.append(li);
//   });
// }

// ─── CART RENDER + REMOVE BUTTON ───
function renderCart() {
  const ul = $("#cart-items");
  ul.innerHTML = "";

  cart.forEach((p, i) => {
    const li = document.createElement("li");
    li.textContent = `${p.name} – ${p.points} AP`;

    // create remove button
    const btn = document.createElement("button");
    btn.className = "cart-remove-btn";
    btn.textContent = "×";
    btn.title = "Șterge din coș";
    btn.onclick = () => {
      cart.splice(i, 1); // remove from array
      $("#cart-count").textContent = cart.length;
      renderCart(); // re-render list
    };

    li.append(btn);
    ul.append(li);
  });
}

// // ─── CHECKOUT ───
// $("#checkout-btn").onclick = () => {
//   if (!cart.length) {
//     alert("Coș gol");
//   } else {
//     alert("Checkout finalizat!");
//     cart = [];
//     $("#cart-count").textContent = 0;
//     $("#cart-modal").classList.add("hidden");
//     renderGrid();
//   }
// };

// ─── CHECKOUT (unchanged, but clears cart) ───
$("#checkout-btn").onclick = () => {
  if (!cart.length) {
    alert("Coș gol");
  } else {
    alert("Checkout finalizat!");
    cart = [];
    $("#cart-count").textContent = 0;
    $("#cart-modal").classList.add("hidden");
    renderGrid();
  }
};

// ─── SEARCH & FILTER ───
$("#search-input").addEventListener("input", renderGrid);
$("#filter-select").addEventListener("change", renderGrid);

// ─── BANNER SLIDER ───
let currentBanner = 0;
function showBanner(i) {
  $all(".banner-img").forEach((img, idx) =>
    img.classList.toggle("active", idx === i)
  );
  $all(".dot").forEach((dot, idx) => dot.classList.toggle("active", idx === i));
  currentBanner = i;
}
$all(".dot").forEach((dot) => {
  dot.onclick = () => showBanner(+dot.dataset.index);
});
setInterval(() => showBanner((currentBanner + 1) % 4), 5000);

// ─── INIT ───
document.addEventListener("DOMContentLoaded", renderGrid);