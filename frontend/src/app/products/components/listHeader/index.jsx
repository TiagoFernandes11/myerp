"use client";

import { useRouter } from "next/navigation";
import "./index.css";
import { useState } from "react";

export default function ListHeader({ product, selectedFilter, searchKeyword, setSelectedFilter, setSearchKeyword }) {
  
  const router = useRouter();
  return (
    <div>
      <div className="buttons">
        <button
          onClick={() =>
            router.push("http://localhost:3000/products/" + product.id)
          }
        >
          Edit
        </button>
        <button
          onClick={() => {
            router.push("http://localhost:3000/products/new");
          }}
        >
          Create
        </button>
      </div>
      <div className="search">
        <select
          onChange={(e) => {
            setSelectedFilter(e.target.value);
          }}
        >
          <option value="">Select and option</option>
          <option value="name">Name</option>
          <option value="sku">sku</option>
          <option value="price">price</option>
        </select>
        <input
          type="text"
          name="serachKeyword"
          placeholder="Serch"
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <button
          onClick={() =>
            router.push(
              "/products?page=0&filter=" +
                selectedFilter +
                "&value=" +
                searchKeyword
            )
          }
        >
          Search
        </button>
      </div>
    </div>
  );
}
