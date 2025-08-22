"use client";

import Link from "next/link";
import Image from "next/image";
import "./index.css";
import { useEffect, useState } from "react";

export default function Header({ isLoggedIn, setIsLoggedIn }) {
  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsLoggedIn(!!token);
  }, []);

  return (
    <nav>
      <ul>
        <Link href={"/"}>
          <Image src={"./next.svg"} alt="logo" width={100} height={10} />
        </Link>
      </ul>
      <div className="buttons">
        {isLoggedIn || (
          <>
            <Link className="button" href={"/client/login"}>
              <h3>Login</h3>
            </Link>
            <Link className="button" href={"/client/register"}>
              <h3>Register</h3>
            </Link>
          </>
        )}
        {isLoggedIn && (
          <Link
            className="button"
            onClick={() => {
              localStorage.removeItem("token");
              setIsLoggedIn(false);
            }}
            href={"/"}
          >
            <h3>Logout</h3>
          </Link>
        )}

        <Link className="button" href={"/shopping-cart"}>
          <h3>ShoppingCart</h3>
        </Link>
      </div>
    </nav>
  );
}
