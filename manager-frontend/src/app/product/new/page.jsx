"use client";

import "./page.css";

import { useState } from "react";
import AuthGuard from "../../security/AuthGuard";
import FormHeader from "@/app/components/lists/FormHeader";
import EntityForm from "@/app/components/lists/EntityForm";
import { createProduct, deleteProduct } from "../service/productService";

export default function NewProduct() {
  const [product, setProduct] = useState({
    id: "",
    sku: "",
    name: "",
    price: 0.0,
  });

  return (
    <AuthGuard>
      <FormHeader
        entity={product}
        entityName={"product"}
        saveFunction={createProduct}
        deleteFunction={deleteProduct}
      />
      <EntityForm entity={product} setEntity={setProduct} isNew={true} />
    </AuthGuard>
  );
}
