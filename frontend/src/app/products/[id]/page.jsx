"use client";

import "./page.css";

import AuthGuard from "../../security/AuthGuard";

import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import FormHeader from "../components/formHeader";
import ProductForm from "../components/productForm";
import { getProductById } from "../service/productService";

export default function ClientByID() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    if (!id) {
      console.error("Not a valid id");
      return;
    }
    getProductById(id).then((product) => setProduct(product));
  }, [id]);

  return (
    <AuthGuard>
      <FormHeader product={product} isNew={false} />
      <ProductForm
        product={product ? product : null}
        setProduct={setProduct}
        isNew={false}
      />
    </AuthGuard>
  );
}
