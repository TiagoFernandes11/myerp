"use client";

import "./page.css";

import AuthGuard from "../../security/AuthGuard";

import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import FormHeader from "@/app/components/lists/FormHeader";
import EntityForm from "@/app/components/lists/EntityForm";
import {
  getProductById,
  updateProduct,
  deleteProduct,
} from "../service/productService";

export default function ClientByID() {
  const { id } = useParams();
  const [product, setProduct] = useState({
    id: "",
    sku: "",
    name: "",
    price: 0.0,
  });

  useEffect(() => {
    if (!id) {
      console.error("Not a valid id");
      return;
    }
    getProductById(id).then((product) => {
      if (product) {
        setProduct(product);
      }
    });
  }, [id]);

  return (
    <AuthGuard>
      <FormHeader
        entity={product}
        entityName={"product"}
        saveFunction={updateProduct}
        deleteFunction={deleteProduct}
      />
      <EntityForm entity={product} setEntity={setProduct} isNew={false} />
    </AuthGuard>
  );
}
