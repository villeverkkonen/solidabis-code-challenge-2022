import { shallowMount } from "@vue/test-utils";
import CodeChallenge from "../components/CodeChallenge.vue";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import axios from "axios";
import flushPromises from "flush-promises";

describe("CodeChallenge.vue", () => {
  let wrapper = null;

  vi.mock("axios", () => {
    return {
      default: {
        get: vi.fn(),
      },
    };
  });

  beforeEach(async () => {
    const responseGet = {
      data: [
        {
          name: "Apple",
          attack: 8.3,
          defence: 0.2,
          health: 27,
          delay: 0.9,
        },
        {
          name: "Banana",
          attack: 18.3,
          defence: 1.2,
          health: 46.6,
          delay: 2,
        },
        {
          name: "Pineapple",
          attack: 11.2,
          defence: 0.5,
          health: 33.3,
          delay: 1.2,
        },
      ],
    };

    axios.get.mockResolvedValue(responseGet);
    wrapper = shallowMount(CodeChallenge);

    // Wait until component has mounted
    await flushPromises();
  });

  afterEach(() => {
    axios.get.mockReset();
    wrapper.unmount();
  });

  it("should get fighters", () => {
    expect(axios.get).toHaveBeenCalledTimes(1);
    expect(wrapper.vm.fighters.length === 3);
  });

  it("should render two fighter divs", () => {
    const fighterSelectDivs = wrapper.findAll(".fighterSelect");

    const fighterSelectOne = fighterSelectDivs.at(0);
    const fighterSelectOneStats = wrapper
      .find(".fighterSelectOneStats")
      .findAll("p");
    expect(fighterSelectOne.find("h3").text()).toMatch("Fighter 1");
    expect(fighterSelectOneStats.at(0).text()).toMatch("Name: Apple");
    expect(fighterSelectOneStats.at(1).text()).toMatch("Health: 27");
    expect(fighterSelectOneStats.at(2).text()).toMatch("Attack: 8.3");
    expect(fighterSelectOneStats.at(3).text()).toMatch("Defence: 0.2");
    expect(fighterSelectOneStats.at(4).text()).toMatch("Delay: 0.9");

    const fighterSelectTwo = fighterSelectDivs.at(1);
    const fighterSelectTwoStats = wrapper
      .find(".fighterSelectTwoStats")
      .findAll("p");
    expect(fighterSelectTwo.find("h3").text()).toMatch("Fighter 2");
    expect(fighterSelectTwoStats.at(0).text()).toMatch("Name: Pineapple");
    expect(fighterSelectTwoStats.at(1).text()).toMatch("Health: 33.3");
    expect(fighterSelectTwoStats.at(2).text()).toMatch("Attack: 11.2");
    expect(fighterSelectTwoStats.at(3).text()).toMatch("Defence: 0.5");
    expect(fighterSelectTwoStats.at(4).text()).toMatch("Delay: 1.2");
  });
});
