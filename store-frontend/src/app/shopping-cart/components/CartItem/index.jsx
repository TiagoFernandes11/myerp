"use client";

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
    <div className="cart-item">
      <Image
        alt={product.name}
        src={product.image && product.image.trim() !== "" ? product.image : "/default-image.png"}
        width={80}
        height={80}
        className="cart-item-image"
        priority
      />
      <div className="cart-item-details">
        <p className="cart-item-name">{product.name}</p>
        <p className="cart-item-price">
          R$ {Number(product.price).toFixed(2) * product.quantity}
        </p>
        <p className="cart-item-quantity">Qtd: {product.quantity}</p>
        <div className="cart-item-actions">
          <button
            className="add-product"
            onClick={addProduct}
            aria-label="Adicionar"
          >
            +
          </button>
          <button
            className="remove-product"
            onClick={removeProduct}
            aria-label="Remover"
          >
            -
          </button>
        </div>
      </div>
    </div>
  );
}
