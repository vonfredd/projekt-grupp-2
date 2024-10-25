import { describe, it, expect, beforeEach, vi } from "vitest";
import { mount } from "@vue/test-utils";
import MovieProfileView from "@/views/MovieProfileView.vue";

describe("MovieProfileView", () => {
  let wrapper;

  const movie = {
    name: "Inception",
    description:
        "A mind-bending thriller where dream invasion is possible. Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction: stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable.",
    genres: [
      "Action",
      "Science Fiction",
      "Adventure"
    ],
    duration: 148,
    releaseDate: "2010-07-16",
    imageUrl: "/ljsZTbVsrQSqZgWeep2B1QiDKuh.jpg",
    averageRating: 8.8,
    backdropPath: "/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg",
  };

  beforeEach(() => {

    global.fetch = vi.fn(() =>
        Promise.resolve({
          json: () => Promise.resolve(movie),
        })
    );

    // Mock window.scrollTo
    window.scrollTo = vi.fn();

    wrapper = mount(MovieProfileView, {
      props: {
        id: "1",
      },
    });
  });

  it("renders movie title", () => {
    expect(wrapper.text()).toContain("Inception");
  });

  it("renders movie description", () => {
    expect(wrapper.text()).toContain("A mind-bending thriller");
  });

  it("renders movie duration in hours and minutes", () => {
    expect(wrapper.text()).toContain("2h 28m");
  });

  it("renders movie rating", () => {
    expect(wrapper.text()).toContain("8.8%");
  });


  it("renders movie genres", () => {
    expect(wrapper.text()).toContain("Action");
    expect(wrapper.text()).toContain("Science Fiction");
    expect(wrapper.text()).toContain("Adventure");
  });

  it("renders movie background image with correct URL", () => {
    const mainElement = wrapper.find('main');
    expect(mainElement.attributes('style')).toContain(`background-image: url(https://image.tmdb.org/t/p/w500/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg)`);
  });

});
