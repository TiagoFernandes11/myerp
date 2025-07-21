export function createShoppingCart(item, quantity) {
  item.quantity = quantity;

  const shoppingCart = {
    item: [item],
    total: item.price * [quantity],
    createdAt: Date.now(),
  };

  localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart));
}

export function addItemToShoppingCart(item) {
  const shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"));

  if (shoppingCart) {
    const existingItem = shoppingCart.item.find((i) => i.id === item.id);
    if (existingItem) {
      existingItem.quantity++;
    } else {
      shoppingCart.item.push(item);
    }
    let subtotal = 0;

    shoppingCart.item.forEach((item) => {
      subtotal += Number(item.quantity) * Number(item.price);
    });

    shoppingCart.total = subtotal;

    localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart));
  } else {
    createShoppingCart(item, 1);
  }
}

export function removeItemOfShoppingCart(item) {
  const shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"));
  const existingItem = shoppingCart.item.find((i) => i.id === item.id);

  if (existingItem) {
    if (existingItem.quantity > 1) {
      existingItem.quantity--;
    } else {
      shoppingCart.item = shoppingCart.item.filter((i) => i.id !== item.id);
    }
  }

  let subtotal = 0;

  shoppingCart.item.forEach((item) => {
    subtotal += Number(item.quantity) * Number(item.price);
  });

  shoppingCart.total = subtotal;

  localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart));
}
