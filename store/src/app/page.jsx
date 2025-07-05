"use client";

import "./page.css";
import Showcase from "./components/Showcase";
import { useRouter } from "next/navigation";

export default function HomePage() {
  const router = useRouter();

  return (
    <div>
      <Showcase />
    </div>
  );
}
