"use client";

import { useRouter } from "next/navigation";
import CartItem from "./components/CartItem";
import { getShoppingCart } from "./service/ShoppingCartService";
import "./page.css";
import { useEffect, useState } from "react";

export default function ShoppingCartPage() {
  const router = useRouter();
  const [shoppingCart, setShoppingCart] = useState();

  useEffect(() => {
    async function fetchShoppingCart() {
      const cart = await getShoppingCart();
      setShoppingCart(cart);
      console.log("Shopping cart fetched:", cart);
    }
    fetchShoppingCart();
  }, []);

  return (
    <div className="body">
      <h1>My Cart</h1>
      <div className="shopping-cart">
        <div className="resume">
          <p>Items</p>
          <div className="items">
            {shoppingCart && shoppingCart.itens?.length > 0 ? (
              shoppingCart.itens.map((item) => (
                <CartItem
                  setShoppingCart={setShoppingCart}
                  key={item.id}
                  item={item}
                />
              ))
            ) : (
              <div>No products in your shopping cart</div>
            )}
          </div>
        </div>
        <div className="options">
          <div className="summary-row">
            <span>Subtotal:</span>
            <span>R$ {shoppingCart?.subtotal?.toFixed(2) || "0.00"}</span>
          </div>
          <div className="summary-row">
            <span>Shipping:</span>
            <span>R$ {shoppingCart?.shipping?.toFixed(2) || "0.00"}</span>
          </div>
          <hr />
          <div className="summary-row total">
            <span>Total:</span>
            <span>R$ {shoppingCart?.total?.toFixed(2) || "0.00"}</span>
          </div>
          <hr />
          <div className="actions">
            {shoppingCart &&
            shoppingCart.itens &&
            shoppingCart.itens.length > 0 ? (
              <button
                className="checkout-btn"
                onClick={() => router.push("/payment-confirmation")}
              >
                Checkout
              </button>
            ) : (
              <button className="checkout-btn" disabled>
                Checkout
              </button>
            )}
            <button
              className="add-btn"
              onClick={() => router.push("/products")}
            >
              Add more items
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
