"use client";

import { useRouter } from "next/navigation";
import CartItem from "./components/CartItem";
import "./page.css";

export default function ShoppingCartPage() {
  const router = useRouter();

  let shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"));

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
                    key={product.id}
                    name={product.name}
                    price={product.price * product.quantity}
                    quantity={product.quantity}
                    image={null}
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
            {shoppingCart &&
            shoppingCart.item &&
            shoppingCart.item.length > 0 ? (
              <button
                onClick={() => {
                  router.push("/payment-confirmation");
                }}
              >
                Checkout
              </button>
            ) : (
              <div></div>
            )}
            <button>Add more itens</button>
          </div>
        </div>
      </div>
    </div>
  );
}
