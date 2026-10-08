#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

echo "=========================================================="
echo " Spring AI Course: Compiling Bilingual Markdown to PDFs"
echo " Root: ${ROOT_DIR}"
echo "=========================================================="

# Find all lesson notes markdown files
find_markdown_files() {
    find "${ROOT_DIR}" -type f \( -name "lesson-notes.md" -o -name "ders-notlari.md" -o -name "capstone-guide.md" -o -name "bitirme-projesi-rehberi.md" \)
}

check_tool() {
    if command -v npx >/dev/null 2>&1; then
        echo "marp"
    elif command -v pandoc >/dev/null 2>&1; then
        echo "pandoc"
    else
        echo "none"
    fi
}

TOOL=$(check_tool)
echo "Detected PDF generation engine: ${TOOL}"

for md_file in $(find_markdown_files); do
    pdf_file="${md_file%.md}.pdf"
    if [ ! -f "${pdf_file}" ] || [ "${md_file}" -nt "${pdf_file}" ]; then
        echo "Processing: ${md_file} -> ${pdf_file}"

        if [ "${TOOL}" = "marp" ]; then
            npx -y @marp-team/marp-cli@latest "${md_file}" --pdf --allow-local-files -o "${pdf_file}" || {
                echo "Marp export had a warning or fallback, continuing..."
            }
        elif [ "${TOOL}" = "pandoc" ]; then
            pandoc "${md_file}" -o "${pdf_file}" || {
                echo "Pandoc export had a warning, continuing..."
            }
        else
            echo "No PDF engine detected. Install Node.js/npx or Pandoc to build PDFs from markdown."
        fi
    else
        echo "Up-to-date: ${pdf_file}"
    fi
done

echo "=========================================================="
echo " PDF generation process finished!"
echo "=========================================================="
