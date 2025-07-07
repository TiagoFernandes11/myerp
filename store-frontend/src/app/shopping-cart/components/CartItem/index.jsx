"use client";

import "./index.css";

import Image from "next/image";

export default function CartItem({ name, price, quantity, image }) {
  return (
    <div>
      <Image alt="Product picure" src={image} />
      <div>
        <p>{name}</p>
        <p>R$ {price}</p>
        <p>qtd. {quantity}</p>
      </div>
    </div>
  );
}
