"use client";

import "./index.css";

import Product from "../product";
import { useEffect, useState } from "react";
import { getProducts } from "../../service/productService";

export default function ProductList({
  selectedProduct,
  setSelectedProduct,
  currentPage,
  selectedFilterValue,
  searchKeyword,
}) {
  const [productList, setProductList] = useState([]);

  useEffect(() => {
    async function fetchProducts(page, filter, value) {
      const products = await getProducts(page, filter, value);
      setProductList(products);
    }

    fetchProducts(currentPage, selectedFilterValue, searchKeyword);
  }, [currentPage, selectedFilterValue, searchKeyword]);

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
