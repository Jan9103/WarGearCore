{ pkgs ? import <nixpkgs> {} }: pkgs.mkShell {
  nativeBuildInputs = with pkgs; [
    bash
    curl
    git
    gnumake
    jdk17
    unzip
    zip
  ];
}
