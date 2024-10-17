/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",],
  theme: {
    extend: {
          colors: {
              primary: '#CC0028',
              secondary: '#7D1128',
              third: '#F34444',
            greyish: '#D9D9D9',
            darkgrey: '#3D3C3C',
          },
    },
  },
  plugins: [],
}

