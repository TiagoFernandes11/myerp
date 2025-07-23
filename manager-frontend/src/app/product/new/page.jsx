"use client";

import "./page.css";

import { useState } from "react";
import AuthGuard from "../../security/AuthGuard";
import FormHeader from "@/app/components/lists/FormHeader";
import EntityForm from "@/app/components/lists/EntityForm";
import { createProduct, deleteProduct } from "../service/productService";

export default function NewProduct() {
  const [selectedImage, setSelectedImage] = useState();
  const [product, setProduct] = useState({
    id: "",
    sku: "",
    name: "",
    price: 0.0,
  });

  function handleImageSubmit(image) {
    const formData = new FormData();
    setSelectedImage(image);
    setProduct((prev) => ({
      ...prev,
      image,
    }));
  }

  return (
    <AuthGuard>
      <FormHeader
        entity={product}
        entityName={"product"}
        saveFunction={createProduct}
        deleteFunction={deleteProduct}
      />
      <EntityForm entity={product} setEntity={setProduct} isNew={true} />
      {selectedImage && (
        <div>
          <img
            src={URL.createObjectURL(selectedImage)}
            alt="not found"
            width={"200px"}
          />
        </div>
      )}
      <input
        type="file"
        onChange={(event) => handleImageSubmit(event.target.files[0])}
      />
    </AuthGuard>
  );
}
