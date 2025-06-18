"use client";

import { useRouter } from "next/navigation";
import "./index.css";

export default function ListHeader({ product }) {
  const router = useRouter();
  return (
    <div>
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
  );
}
