"use client";

import "./page.css";

import { useState } from "react";
import AuthGuard from "../../security/AuthGuard";
import ProductForm from "../components/productForm";
import FormHeader from "../components/formHeader";

export default function NewProduct() {
  const [product, setProduct] = useState({});

  return (
    <AuthGuard>
      <FormHeader isNew={true} product={product} />
      <ProductForm isNew={true} product={product} setProduct={setProduct} />
    </AuthGuard>
  );
}
