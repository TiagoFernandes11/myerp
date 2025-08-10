"use client";

import {
  addItemToShoppingCart,
  getShoppingCart,
  removeItemOfShoppingCart,
} from "../../service/ShoppingCartService";
import "./index.css";

import Image from "next/image";

export default function CartItem({
  item,
  setShoppingCart,
  shoppingCart,
  total,
}) {
  async function addProduct() {
    addItemToShoppingCart(item);
    setShoppingCart(await getShoppingCart(localStorage.getItem("clientIdErp")));
  }

  async function removeProduct() {
    removeItemOfShoppingCart(item);
    setShoppingCart(await getShoppingCart(localStorage.getItem("clientIdErp")));
  }

  return (
    <div className="cart-item">
      <Image
        alt={item.name}
        src={
          item.image && item.image.trim() !== ""
            ? product.image
            : "/default-image.png"
        }
        width={80}
        height={80}
        className="cart-item-image"
        priority
      />
      <div className="cart-item-details">
        <p className="cart-item-name">{item.name}</p>
        <p className="cart-item-price">R$ {Number(item.total).toFixed(2)}</p>
        <p className="cart-item-quantity">Qtd: {item.quantity}</p>
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
