import "./index.css";

import ShowcaseItem from "../ShowcaseItem";
import { useEffect, useState } from "react";
import { getProducts } from "@/app/service/homeService";
import { addItemToShoppingCart } from "@/app/shopping-cart/service/ShoppingCartService";

export default function Showcase() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    async function getShowcaseItems() {
      const products = await getProducts();
      setProducts(products);
    }

    getShowcaseItems();
  }, []);

  return (
    <div className="showcase-container">
      {products.map((product) => {
        return (
          <ShowcaseItem
            key={product.id}
            id={product.id}
            image={product.image}
            name={product.name}
            price={product.price}
            buttonAction={() => addItemToShoppingCart(product)}
          />
        );
      })}
    </div>
  );
}
