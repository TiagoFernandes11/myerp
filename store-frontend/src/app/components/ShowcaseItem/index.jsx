"use client";

import { useRouter } from "next/navigation";
import "./index.css";

import Image from "next/image";

export default function ShowcaseItem({ id, image, name, price, buttonAction }) {
  const router = useRouter();

  return (
    <div className="item-container">
      <img
        src={`data:image/*;base64,${image}`}
        alt={name}
        onClick={() => router.push("http://localhost:4000/products/" + id)}
      />
      <div className="name-price-container">
        <span
          className="name"
          onClick={() => router.push("http://localhost:4000/products/" + id)}
        >
          {name}
        </span>
        <span className="price">R$ {Number(price).toFixed(2)}</span>
      </div>
      <div className="button-container">
        <button onClick={buttonAction}>Buy</button>
      </div>
    </div>
  );
}
