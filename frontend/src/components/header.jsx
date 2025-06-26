import Image from "next/image";
import Link from "next/link";

export default function Header(links) {
  return (
    <nav className="bg-gray-800 p-4">
      <ul className="flex space-x-4">
        <Link href={"/"}>
          <Image src={"./next.svg"} alt="logo" width={100} height={10} />
        </Link>

        {links.addresses.map((linkItem, index) => (
          <li key={index}>
            <Link className="hover:text-blue-400" href={linkItem.url}>
              {linkItem.label}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}
