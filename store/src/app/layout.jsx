"use client";

import Header from "./components/Header";
import "./globals.css";

import { usePathname } from "next/navigation";

export default function RootLayout({ children }) {
  if (usePathname() === "/login") {
    return (
      <html lang="en">
        <body className="w-full h-full">
          <div>{children}</div>
        </body>
      </html>
    );
  }
  return (
    <html lang="en">
      <body>
        <Header />
        {children}
      </body>
    </html>
  );
}
