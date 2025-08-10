"use client";

import {
  addItemToShoppingCart,
  getShoppingCart,
  removeItemOfShoppingCart,
} from "../../service/ShoppingCartService";
import "./index.css";

import Image from "next/image";

export default function CartItem({ item, setShoppingCart }) {
  async function addProduct() {
    await addItemToShoppingCart(item);
    const updatedCart = await getShoppingCart(
      localStorage.getItem("clientIdErp")
    );
    setShoppingCart(updatedCart);
  }

  async function removeProduct() {
    await removeItemOfShoppingCart(item);
    const updatedCart = await getShoppingCart(
      localStorage.getItem("clientIdErp")
    );
    setShoppingCart(updatedCart);
  }

  return (
    <div className="cart-item">
      <Image
        alt={item.name}
        src={
          item.image && item.image.trim() !== ""
            ? item.image
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
