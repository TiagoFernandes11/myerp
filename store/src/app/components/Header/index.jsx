import Link from "next/link";
import Image from "next/image";
import "./index.css";

export default function Header() {
  return (
    <nav>
      <ul className>
        <Link href={"/"}>
          <Image src={"./next.svg"} alt="logo" width={100} height={10} />
        </Link>
      </ul>
      <div>
        <input type="text" placeholder="What are you looking for ?" />
        <button>search</button>
      </div>
      <div className="buttons">
        <Link className="button" href={"/"}>
          <h3>Login</h3>
        </Link>
        <Link className="button" href={"/"}>
          <h3>Logout</h3>
        </Link>
        <Link className="button" href={"/"}>
          <h3>Carrinho</h3>
        </Link>
      </div>
    </nav>
  );
}
