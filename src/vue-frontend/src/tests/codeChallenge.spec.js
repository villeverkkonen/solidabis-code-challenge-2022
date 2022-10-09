import { shallowMount } from "@vue/test-utils";
import CodeChallenge from "../components/CodeChallenge.vue";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import axios from "axios";

describe("CodeChallenge.vue", () => {
  let wrapper = null;

  vi.mock("axios", () => {
    return {
      default: {
        get: vi.fn(),
      },
    };
  });

  beforeEach(() => {
    const responseGet = {
      data: [
        {
          name: "Apple",
          carbohydrate: 8.3,
          protein: 0.2,
          energy: 169.6,
          fat: 0.1,
        },
        {
          name: "Banana",
          carbohydrate: 18.3,
          protein: 1.2,
          energy: 366.4,
          fat: 0.4,
        },
        {
          name: "Pineapple",
          carbohydrate: 11.2,
          protein: 0.5,
          enegy: 232.7,
          fat: 0.4,
        },
      ],
    };

    axios.get.mockResolvedValue(responseGet);
    wrapper = shallowMount(CodeChallenge);
  });

  afterEach(() => {
    axios.get.mockReset();
    wrapper.unmount();
  });

  it("should get foods", () => {
    expect(axios.get).toHaveBeenCalledTimes(1);
    expect(wrapper.vm.foods.length === 3);
  });

  it("should render page header", () => {
    expect(wrapper.text()).toMatch("The Masters of the Foodverse");
  });
});
