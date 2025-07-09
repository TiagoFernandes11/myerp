export function confirmOrder() {
  const shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"));
  shoppingCart.clientId = localStorage.getItem("clientId");

  console.log(shoppingCart);
}
