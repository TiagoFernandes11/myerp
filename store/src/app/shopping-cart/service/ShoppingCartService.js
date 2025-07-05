

export function createShoppingCart(clientId, item, quantity){
    item.quantity = quantity;

    const shoppingCart = {
        clientId,
        item: [item],
        total: item.price * quantity,
        createdAt: Date.now(),
    }

    localStorage.setItem("shoppingCart", shoppingCart);
}