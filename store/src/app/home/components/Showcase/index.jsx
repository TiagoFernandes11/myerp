import "./index.css";

import ShowcaseItem from "../ShowcaseItem";
import { useEffect, useState } from "react";
import { getProducts } from "../../service/homeService";

export default function Showcase() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    async function getShowcaseItems() {
      return getProducts();
    }

    setProducts(getShowcaseItems());
  }, []);

  return (
    <div className="showcase-container">
      {products.map((product) => {
        return (
          <ShowcaseItem
            image={product.image}
            name={product.name}
            price={product.price}
          />
        );
      })}
    </div>
  );
}
