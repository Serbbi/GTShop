const url = "https://terrific-renewal-production-759e.up.railway.app/api";
const $ = (s) => document.querySelector(s);
let rewards = [];
let userProfile = null;
let cartRewards = {};

// ───── START API CALLS ─────

// user data
async function fetchUserProfile() {
  try {
    const response = await fetch(url + "/user/profile");
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    userProfile = await response.json();
    const usernameSpan = document.querySelector(".user-name");
    const userActivityPointsSpan = document.querySelector(".user-points");

    if (usernameSpan && userProfile.name) {
      usernameSpan.textContent = userProfile.name;
    }

    if (userActivityPointsSpan && userProfile.activityPoints) {
      userActivityPointsSpan.textContent = userProfile.activityPoints + " AP";
    }
  } catch (error) {
    console.log(error);
  }
}

// rewards data
async function fetchRewards() {
  try {
    const response = await fetch(url + "/rewards");
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const data = await response.json();
    rewards = data.rewards.map((reward) => ({
      id: reward.id,
      name: reward.name,
      description: reward.description,
      price: reward.price,
      image: reward.image,
      category: reward.category,
      inStock: reward.inStock,
      stockCount: reward.stockCount,
    }));
    await fetchCartRewards();
    adjustRewardsStock();
    renderGrid();
  } catch (error) {
    console.log(error);
  }
}

// cart rewards data
async function fetchCartRewards() {
  try {
    const response = await fetch(url + "/cart");

    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }

    const data = await response.json();
    data.items.forEach((item) => {
      cartRewards[item.reward.id] = {
        product: rewards.find((p) => p.id === item.reward.id) || item.reward,
        qty: item.quantity,
      };
    });

    updateCartCount();
  } catch (error) {
    console.error("Failed to fetch cart rewards:", error);
  }
}

// add reward to cart
async function addRewardToCart(reward, qty) {
  try {
    const response = await fetch(url + "/cart/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ rewardId: reward.id, quantity: qty }),
    });

    if (!response.ok) {
      console.error(
        "Failed to add reward to cart:",
        response.status,
        response.statusText
      );
      const errorText = await response.text();
      console.error("Server response:", errorText);
    } else {
      fetchCartRewards();
      console.log("Reward added to cart successfully");
    }
  } catch (error) {
    console.error(error);
  }
}

// remove reward from cart
async function removeRewardFromCart(reward) {
  try {
    const response = await fetch(url + "/cart/remove", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ rewardId: reward.id, quantity: 1 }),
    });

    if (!response.ok) {
      console.error(
        "Failed to remove the reward from the cart",
        response.status,
        response.statusText
      );
      const errorText = await response.text();
      console.error("Server response:", errorText);
    } else {
      //fetchCartRewards();
      console.log("Reward removed from cart successfully");
    }
  } catch (error) {
    console.error(error);
  }
}

// checkout
async function checkout() {
  try {
    const response = await fetch(url + "/checkout", { method: "POST" });
    const data = await response.json();
    if (!data.succes) {
      alert(data.message);
    }
  } catch (error) {
    console.log(error);
  }
}

function adjustRewardsStock() {
  rewards = rewards.map((reward) => {
    const cartEntry = cartRewards[String(reward.id)];
    const cartQty = cartEntry ? cartEntry.qty : 0;

    const adjustedStock = reward.stockCount - cartQty;

    return {
      ...reward,
      stockCount: adjustedStock,
      inStock: adjustedStock > 0,
    };
  });
}
// ───── END API CALLS ─────

// ─── RENDER GRID ───
function renderGrid() {
  const grid = $("#product-grid");
  const term = $("#search-input").value.toLowerCase();
  const filter = $("#filter-select").value;
  grid.innerHTML = "";

  rewards.forEach((p) => {
    if (!p.name.toLowerCase().includes(term)) return;
    if (filter && p.category !== filter) return;

    const card = document.createElement("div");
    card.className = "card";
    card.dataset.id = p.id;
    card.innerHTML = `
        <img src="${p.image}" alt="${p.name}" class="product-image">
        <h4>${p.name}</h4>
        <div class="price-line">
          <span class="points">${p.price} AP</span>
          <button class="card-add-btn"${p.stockCount === 0 ? " disabled" : ""}>
            <img src="assets/icons/backpack-icon.png" alt="Adaugă">
          </button>
        </div>
      `;

    const addRewardToCartButton = card.querySelector(".card-add-btn");
    addRewardToCartButton.onclick = async (e) => {
      e.stopPropagation(); // so card.click won’t fire
      if (p.stockCount > 1) {
        await addRewardToCart(p, 1);
        p.stockCount--;
        updateCartCount();
      } else {
        p.stockCount--;
        p.inStock = false;
        renderGrid();
      }
    };

    card.addEventListener("click", (e) => {
      if (e.target.closest(".card-add-btn")) return;
      openRewardModal(p);
    });

    grid.append(card);
  });
}

function updateProfileActivityPoints(profile = userProfile) {
  if (!profile) return;
  $(".user-points").textContent = `${profile.activityPoints} AP`;
}

function updateCartCount(cart = cartRewards) {
  const total = Object.values(cart).reduce((sum, e) => sum + e.qty, 0);
  $("#cart-count").textContent = total;
}

// ───── START MODALS ─────
// reward modal
function openRewardModal(reward) {
  let minCountValue = 1;
  let maxCountvalue = reward.stockCount;
  let currentCountValue = 1;

  $("#modal-img").src = reward.image;
  $("#modal-name").textContent = reward.name;
  $("#modal-desc").textContent = reward.description;
  $("#modal-points").textContent = `${reward.price} AP`;

  const stockLabel = $("#modal-stock");
  const qtyDisplay = $("#modal-qty");
  const btnDec = $("#modal-decrease");
  const btnInc = $("#modal-increase");
  const addBtn = $("#modal-add");

  if (currentCountValue === minCountValue) {
    btnDec.disabled = true;
  }
  if (currentCountValue === maxCountvalue) {
    btnInc.disabled = true;
  }

  if (maxCountvalue <= 0) {
    stockLabel.textContent = "Stoc epuizat";
    qtyDisplay.textContent = "0";
    btnDec.disabled = true;
    btnInc.disabled = true;
    addBtn.disabled = true;
    addBtn.textContent = "Stoc epuizat";
  } else {
    stockLabel.textContent = "In stoc";
    qtyDisplay.textContent = currentCountValue;
    addBtn.disabled = false;
    addBtn.textContent = "Adaugă";

    btnDec.disabled = true;
    btnDec.onclick = () => {
      if (currentCountValue > 1) {
        currentCountValue--;
        qtyDisplay.textContent = currentCountValue;
        btnInc.disabled = false;
        btnDec.disabled = currentCountValue <= 1;
      }
    };

    btnInc.disabled = currentCountValue >= maxCountvalue;
    btnInc.onclick = () => {
      if (currentCountValue < maxCountvalue) {
        currentCountValue++;
        qtyDisplay.textContent = currentCountValue;
        btnDec.disabled = false;
        btnInc.disabled = currentCountValue >= maxCountvalue;
      }
    };

    addBtn.onclick = async () => {
      await addRewardToCart(reward, currentCountValue);
      reward.stockCount -= currentCountValue;
      if (reward.stockCount === 0) {
        reward.inStock = false;
      }
      updateCartCount();
      closeModal("#product-modal");
      renderGrid();
    };
  }

  $("#product-modal").classList.remove("hidden");
}

function renderCart() {
  const container = document.querySelector(".cart-list");
  container.innerHTML = "";

  Object.values(cartRewards)
    .filter((e) => e.qty > 0)
    .forEach((e) => {
      let { product, qty } = e;
      let max = product.stockCount;
      let reward = rewards.filter((r) => r.id === product.id)[0];

      const item = document.createElement("div");
      item.className = "cart-item";
      item.innerHTML = `
        <img src="${product.img}" alt="${product.name}">
        <div class="cart-item-name">${product.name}</div>
        <div class="cart-item-controls">
          <button class="decrease">−</button>
          <span class="qty">${qty}</span>
          <span class="unit">buc.</span>
         <button class="increase" ${max === 0 ? "disabled" : ""}>+</button>
        </div>
        <div class="cart-item-points">${product.price * qty} AP</div>
      `;

      item.querySelector(".decrease").onclick = async () => {
        if (qty > 0) {
          qty--;
          max++;
          reward.stockCount++;
          await removeRewardFromCart(reward);
          cartRewards[product.id].qty = qty;

          item.querySelector(".qty").textContent = qty;
          item.querySelector(".cart-item-points").textContent =
            product.price * qty + " AP";
          item.querySelector(".increase").disabled = false;
          if (e.qty === 0) {
            delete cartRewards[product.id];
            renderCart();
          }
        }
      };

      item.querySelector(".increase").onclick = () => {
        if (max > 0) {
          qty++;
          max--;
          reward.stockCount--;
          cartRewards[product.id].qty = qty;
          if (max === 0) {
            item.querySelector(".increase").disabled = true;
          }
          item.querySelector(".qty").textContent = qty;
          item.querySelector(".cart-item-points").textContent =
            product.price * qty + " AP";
        } else {
          item.querySelector(".increase").disabled = true;
        }
      };

      container.append(item);
    });

  const totalAP = Object.values(cartRewards).reduce(
    (sum, e) => sum + e.product.price * e.qty,
    0
  );
  document.querySelector(".cart-total").textContent = `Total: ${totalAP} AP`;
  document
    .getElementById("checkout-btn")
    .classList.toggle("hidden", totalAP === 0);
  document.querySelector("#close-cart-modal").onclick = () => {
    closeModal("#cart-modal");
    renderGrid();
    updateCartCount();
  };
}

// ─── MODAL CLOSE / CART MODAL / RENDER CART / CHECKOUT ───
function closeModal(sel) {
  $(sel).classList.add("hidden");
}

$("#close-product-modal").onclick = () => closeModal("#product-modal");
$("#close-cart-modal").onclick = () => closeModal("#cart-modal");
$("#cart-button").onclick = async () => {
  await fetchCartRewards();
  renderCart();
  $("#cart-modal").classList.remove("hidden");
};

$("#checkout-btn").onclick = async () => {
  await checkout();
  await fetchUserProfile();

  const profile = await fetchUserProfile();
  updateProfileActivityPoints(profile);
  updateCartCount();
  closeModal("#cart-modal");
  renderGrid();
};

// ─── INITIAL SETUP ───
$("#search-input").addEventListener("input", renderGrid);
$("#filter-select").addEventListener("change", renderGrid);
renderGrid();

// ─── INIT ───
document.addEventListener("DOMContentLoaded", async () => {
  await fetchUserProfile();
  await fetchRewards();
  $("#search-input").addEventListener("input", renderGrid);
  $("#filter-select").addEventListener("change", renderGrid);
});
