function solution(cacheSize, cities) {
  const cache = [];
  let time = 0;
  const hit = (city) => cache.includes(city);

  if (cacheSize === 0) return cities.length * 5;

  for (let city of cities) {
    city = city.toUpperCase();
    if (hit(city)) {
      cache.splice(cache.indexOf(city), 1);
      time += 1;
    } else if (cache.length === cacheSize) {
      cache.shift();
      time += 5;
    } else {
      time += 5;
    }
    cache.push(city);
  }
  return time;
}
const cacheSize = 3;
const cities = [
  "Jeju",
  "Pangyo",
  "Seoul",
  "NewYork",
  "LA",
  "Jeju",
  "Pangyo",
  "Seoul",
  "NewYork",
  "LA",
];

console.log(solution(cacheSize, cities));
