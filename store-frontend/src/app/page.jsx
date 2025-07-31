"use client";

import "./page.css";
import Showcase from "./components/Showcase";
import { useRouter } from "next/navigation";

export default function HomePage() {
  const router = useRouter();

  return (
    <div className="homepage-container">
      <header className="homepage-header">
        <h1>Welcome to My-Erp store!</h1>
        <p>Check our products and especial offers.</p>
      </header>
      <footer className="homepage-footer">
        <button
          className="homepage-btn"
          onClick={() => router.push("/products")}
        >
          All products
        </button>
      </footer>
    </div>
  );
}
