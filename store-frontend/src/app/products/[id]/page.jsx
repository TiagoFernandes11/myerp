"use client";

import React, { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import axios from "axios";

const fetchProductById = async (id) => {
  const response = (await axios.get("http://localhost:8080/api/product/" + id))
    .data;
  console.log(response);
  return response;
};

export default function ProductPage() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    async function loadProduct() {
      const data = await fetchProductById(id);
      setProduct(data);
    }
    loadProduct();
  }, [id]);

  if (!product) {
    return <div>Carregando produto...</div>;
  }

  return (
    <div style={{ maxWidth: 800, margin: "0 auto", padding: 24 }}>
      <div style={{ display: "flex", gap: 32, alignItems: "flex-start" }}>
        <img
          src={product.image}
          alt={product.name}
          style={{
            width: 320,
            height: 320,
            objectFit: "cover",
            borderRadius: 8,
            boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
          }}
        />
        <div>
          <h1 style={{ marginBottom: 16 }}>{product.name}</h1>
          <p
            style={{
              fontSize: 24,
              fontWeight: "bold",
              color: "#1976d2",
              marginBottom: 16,
            }}
          >
            R$ {product.price}
          </p>
          <p style={{ marginBottom: 24 }}>{product.description}</p>
          <button
            style={{
              padding: "12px 32px",
              background: "#1976d2",
              color: "#fff",
              border: "none",
              borderRadius: 4,
              fontSize: 16,
              cursor: "pointer",
            }}
          >
            Adicionar ao carrinho
          </button>
        </div>
      </div>
    </div>
  );
}
