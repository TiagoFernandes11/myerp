"use client";

import AuthGuard from "../security/AuthGuard";
import ProductList from "./components/productList";
import ListHeader from "./components/listHeader";
import { useState } from "react";

export default function ProductsPage() {
  const [selectedProduct, setSelectedProduct] = useState();

  return (
    <AuthGuard>
      <ListHeader product={selectedProduct} />
      <ProductList
        selectedProduct={selectedProduct}
        setSelectedProduct={setSelectedProduct}
      />
    </AuthGuard>
  );
}
