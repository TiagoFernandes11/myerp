"use client";

import "./index.css";
import {
  createProduct,
  deleteProduct,
  updateProduct,
} from "../../service/productService";
import { useRouter } from "next/navigation";

export default function FormHeader({ product, isNew }) {
  const router = useRouter();

  async function handleSave() {
    if (!isNew) {
      const response = await updateProduct(product);
      console.log(response);
      if (response == 200) {
        alert("Product successfully updated");
        router.push("/products");
      } else {
        alert("Something went wrong: ", response);
      }
    } else {
      const response = await createProduct(product);
      if (response == 201) {
        alert("Product successfully created");
        router.push("/products");
      } else {
        alert("Something went wrong: " + response);
      }
    }
  }

  function handleDelete() {
    if (confirm("Are you sure that you want to delete this product ?")) {
      if (confirm("Please confirm that you want to delete it")) {
        const response = deleteProduct(product);
        if (response === 200) {
          alert("Product successfully deleted");
          router.push("/product");
        } else {
          alert("Something went wrong: " + response);
        }
      }
    }
  }

  return (
    <div>
      <button onClick={handleSave}>Save</button>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
}
