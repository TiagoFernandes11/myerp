"use client";

import "./index.css";

import { getProducts } from "../../service/productService";
import Product from "../product";

const productList = await getProducts();

export default function ProductList({ selectedProduct, setSelectedProduct }) {
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Id: </th>
            <th>SKU: </th>
            <th>Name: </th>
            <th>Price: </th>
          </tr>
        </thead>
        <tbody>
          {productList.map((product) => {
            return (
              <Product
                key={product.id}
                product={product}
                isSelected={selectedProduct?.id === product.id}
                setIsSelected={setSelectedProduct}
              />
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
