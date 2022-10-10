import { shallowMount } from "@vue/test-utils";
import CodeChallenge from "../components/CodeChallenge.vue";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import axios from "axios";
import flushPromises from "flush-promises";

describe("CodeChallenge.vue", () => {
  const responsePost = {
    data: [
      { startGame: ["Apple", [" VS "], "Pineapple"] },
      {
        row: [
          "playerOne",
          "0.9s",
          "Apple hits 1.3 damage.",
          "Pineapple has 32.0 Health.",
        ],
      },
      { row: ["1.2s", "Pineapple hits 7.5 damage.", "Apple has 19.5 Health."] },
      { row: ["1.8s", "Apple hits 1.3 damage.", "Pineapple has 30.5 Health."] },
      { endGame: ["playerOne", "Apple wins the battle!"] },
    ],
  };

  let wrapper = null;

  vi.mock("axios", () => {
    return {
      default: {
        get: vi.fn(),
        post: vi.fn(),
      },
    };
  });

  beforeEach(async () => {
    const responseGet = {
      data: [
        {
          id: 1,
          name: "Apple",
          attack: 8.3,
          defence: 0.2,
          health: 27,
          delay: 0.9,
        },
        {
          id: 2,
          name: "Banana",
          attack: 18.3,
          defence: 1.2,
          health: 46.6,
          delay: 2,
        },
        {
          id: 3,
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

  function nameShouldMatch(element, name) {
    const fighterSelectOneStats = wrapper.find(element).findAll("span");
    expect(fighterSelectOneStats.at(0).text()).toMatch(name);
  }

  function clickFighterChangeButton(element, index) {
    const fighterChangeButtons = wrapper.findAll(element);
    fighterChangeButtons.at(index).trigger("click");
  }

  it("should get fighters", () => {
    expect(axios.get).toHaveBeenCalledTimes(1);
    expect(wrapper.vm.fighters.length === 3);
  });

  it("should render two fighter divs", () => {
    const fighterSelectDivs = wrapper.findAll(".fighterSelect");

    const fighterSelectOne = fighterSelectDivs.at(0);
    const fighterSelectOneStats = wrapper
      .find(".fighterSelectOneStats")
      .findAll("span");
    expect(fighterSelectOneStats.at(0).text()).toMatch("Name: Apple");
    expect(fighterSelectOneStats.at(1).text()).toMatch("Health: 27");
    expect(fighterSelectOneStats.at(2).text()).toMatch("Attack: 8.3");
    expect(fighterSelectOneStats.at(3).text()).toMatch("Defence: 0.2");
    expect(fighterSelectOneStats.at(4).text()).toMatch("Delay: 0.9");

    const fighterSelectTwo = fighterSelectDivs.at(1);
    const fighterSelectTwoStats = wrapper
      .find(".fighterSelectTwoStats")
      .findAll("span");
    expect(fighterSelectTwoStats.at(0).text()).toMatch("Name: Pineapple");
    expect(fighterSelectTwoStats.at(1).text()).toMatch("Health: 33.3");
    expect(fighterSelectTwoStats.at(2).text()).toMatch("Attack: 11.2");
    expect(fighterSelectTwoStats.at(3).text()).toMatch("Defence: 0.5");
    expect(fighterSelectTwoStats.at(4).text()).toMatch("Delay: 1.2");
  });

  it("should fight and show fight logs", async () => {
    axios.post.mockResolvedValue(responsePost);
    wrapper.find("#fightButton").trigger("click");
    // Wait until post completed
    await flushPromises();
    const fightLogStartGame = wrapper.findAll(".fightLog");
    expect(fightLogStartGame.at(0).text()).toContain("Apple");
    expect(fightLogStartGame.at(0).text()).toContain(" VS ");
    expect(fightLogStartGame.at(0).text()).toContain("Pineapple");
  });

  it("should be able to change fighter for player one", async () => {
    const fighterChangeButtons = wrapper.findAll(".fighterChangeButtonsOne");
    const fighterSelectOneStats = wrapper
      .find(".fighterSelectOneStats")
      .findAll("span");
    // By default Apple is chosen
    nameShouldMatch(".fighterSelectOneStats", "Name: Apple");
    // Only button to right should be visible at start
    expect(fighterChangeButtons.length).eq(1);

    // First and only button is for right
    clickFighterChangeButton(".fighterChangeButtonsOne", 0);

    // await or nextTick doesn't seem to work so hard coded timeouts then
    setTimeout(function () {
      nameShouldMatch(".fighterSelectOneStats", "Name: Banana");
      expect(fighterChangeButtons.length).eq(2);

      // Now second button is for right
      clickFighterChangeButton(".fighterChangeButtonsOne", 1);

      setTimeout(function () {
        nameShouldMatch(".fighterSelectOneStats", "Name: Pineapple");
        // Button to right vanishes
        expect(fighterChangeButtons.length).eq(1);

        // Back to one step left
        clickFighterChangeButton(".fighterChangeButtonsOne", 0);

        setTimeout(function () {
          nameShouldMatch(".fighterSelectOneStats", "Name: Banana");
          expect(fighterChangeButtons.length).eq(2);
        }, 100);
      }, 100);
    }, 100);
  });

  it("should be able to change fighter for player two", async () => {
    const fighterChangeButtons = wrapper.findAll(".fighterChangeButtonsTwo");
    // By default Apple is chosen
    nameShouldMatch(".fighterSelectOneStats", "Name: Apple");
    // Only button to right should be visible at start
    expect(fighterChangeButtons.length).eq(1);

    // First and only button is for right
    clickFighterChangeButton(".fighterChangeButtonsTwo", 0);

    // await or nextTick doesn't seem to work so hard coded timeouts then
    setTimeout(function () {
      nameShouldMatch(".fighterSelectOneStats", "Name: Banana");
      expect(fighterChangeButtons.length).eq(2);

      // Now second button is for right
      clickFighterChangeButton(".fighterChangeButtonsTwo", 1);

      setTimeout(function () {
        nameShouldMatch(".fighterSelectOneStats", "Name: Pineapple");
        // Button to right vanishes
        expect(fighterChangeButtons.length).eq(1);

        // Back to one step left
        clickFighterChangeButton(".fighterChangeButtonsTwo", 0);

        setTimeout(function () {
          nameShouldMatch(".fighterSelectOneStats", "Name: Banana");
          expect(fighterChangeButtons.length).eq(2);
        }, 100);
      }, 100);
    }, 100);
  });
});
