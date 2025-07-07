"use client";

import CartItem from "./components/CartItem";
import "./page.css";

export default function ShoppingCartPage() {
  let shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"));

  console.log(shoppingCart);

  return (
    <div className="body">
      <h1>My Cart</h1>
      <div className="shopping-cart">
        <div className="resume">
          <p>Itens</p>
          <div className="itens">
            {shoppingCart ? (
              shoppingCart.item.map((product) => {
                return (
                  <CartItem
                    name={product.name}
                    price={product.price * product.quantity}
                    quantity={product.quantity}
                  />
                );
              })
            ) : (
              <div>No products in your shopping cart</div>
            )}
          </div>
        </div>
        <div className="options">
          <p>Subtotal: </p>
          <p>Shipping: </p>
          <div>
            <hr />
            <div>
              <p>Total</p>
              <p>R$ Placeholder</p>
            </div>
            <hr />
            <button>Checkout</button>
            <button>Add more itens</button>
          </div>
        </div>
      </div>
    </div>
  );
}
