"use client";

import { useEffect, useState } from "react";
import "./page.css";

export default function ShoppingCartPage() {
  const [products, setProducts] = useState([[]]);

  useEffect(() => {
    setProducts([]);
  }, []);

  return (
    <div className="body">
      <h1>My Cart</h1>
      <div className="shopping-cart">
        <div className="resume">
          <p>Itens</p>
          <div className="itens">
            {products.map((product) => {
              return <div>oi</div>;
            })}
          </div>
        </div>
        <div className="options">
          <p>Total: </p>
          <p>Shipping: </p>
          <div>
            <button>Checkout</button>
            <button>Add more itens</button>
          </div>
        </div>
      </div>
    </div>
  );
}
