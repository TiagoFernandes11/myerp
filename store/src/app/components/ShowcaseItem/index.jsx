import "./index.css";

import Image from "next/image";

export default function ShowcaseItem({ image, name, price }) {
  return (
    <div className="item-container">
      <img src={image} alt={name} />
      <div className="name-price-container">
        <span className="name">{name}</span>
        <span className="price">R$ {Number(price).toFixed(2)}</span>
      </div>
      <div className="button-container">
        <button>Comprar</button>
      </div>
    </div>
  );
}
