import "./index.css";

import ShowcaseItem from "../ShowcaseItem";
import { useEffect, useState } from "react";
import { getProducts } from "@/app/service/homeService";
import { addItemToShoppingCart } from "@/app/shopping-cart/service/ShoppingCartService";

export default function Showcase() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  async function getShowcaseItems(filter = "", value = "", pageNum = 0) {
    setLoading(true);
    const products = await getProducts(filter, value, pageNum);
    setProducts(products);
    setLoading(false);
  }

  useEffect(() => {
    getShowcaseItems();
  }, []);

  return (
    <>
      <div className="showcase-search-bar">
        <input
          type="text"
          placeholder="Search products..."
          value={search}
          onChange={(e) => setSearch(e.target.value)} // só atualiza o state
        />
        <button onClick={() => getShowcaseItems("", search, 0)}>Search</button>
      </div>

      <div className="showcase-container">
        {loading && <p>Loading products...</p>}
        {!loading && products.length === 0 && <p>No products available.</p>}
        {!loading &&
          products.length > 0 &&
          products.map((product) => {
            return (
              <ShowcaseItem
                key={product.id}
                id={product.id}
                image={product.image}
                name={product.name}
                price={product.price}
                buttonAction={() => {
                  const item = {
                    productId: product.id,
                    name: product.name,
                    price: product.price,
                    quantity: 1,
                  };
                  addItemToShoppingCart(item);
                }}
              />
            );
          })}
      </div>
    </>
  );
}
