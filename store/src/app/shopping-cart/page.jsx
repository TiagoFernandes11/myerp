"use client";

import { useEffect, useState } from "react";
import "./page.css";

export default function ShoppingCartPage() {
  const [shoppingCart, setShoppingCart] = useState(
    JSON.parse(localStorage.getItem("shoppingCart"))
  );

  return (
    <div className="body">
      <h1>My Cart</h1>
      <div className="shopping-cart">
        <div className="resume">
          <p>Itens</p>
          <div className="itens">
            {shoppingCart.item.map((product) => {
              return <div>product placeholder</div>;
            })}
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
