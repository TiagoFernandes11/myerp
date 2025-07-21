"use client";

import App from "next/app";
import {
  addItemToShoppingCart,
  removeItemOfShoppingCart,
} from "../../service/ShoppingCartService";
import "./index.css";

import Image from "next/image";

export default function CartItem({ product, setShoppingCart, total }) {
  function addProduct() {
    addItemToShoppingCart(product);
    setShoppingCart(JSON.parse(localStorage.getItem("shoppingCart")));
  }

  function removeProduct() {
    removeItemOfShoppingCart(product);
    setShoppingCart(JSON.parse(localStorage.getItem("shoppingCart")));
  }

  return (
    <div>
      <Image alt="Product picure" src={product.image} />
      <div>
        <p>{product.name}</p>
        <p>R$ {total}</p>
        <p>qtd. {product.quantity}</p>
        <div className="buttons">
          <div className="add-product" onClick={addProduct}>
            +
          </div>
          <div className="remove-product" onClick={removeProduct}>
            -
          </div>
        </div>
      </div>
    </div>
  );
}
