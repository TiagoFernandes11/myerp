"use client";

import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import Header from "@/app/components/header";
import SideBar from "@/app/components/sidebar";
import { usePathname } from "next/navigation";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

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
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased h-full overflow-hidden`}
      >
        {" "}
        <Header
          addresses={[
            { label: "clients", url: "/client?page=0&filter=&value=" },
            { label: "products", url: "/product?page=0&filter=&value=" },
          ]}
        ></Header>
        <div style={{ display: "flex", height: "100%", width: "100%" }}>
          <SideBar></SideBar>
          {children}
        </div>
      </body>
    </html>
  );
}
