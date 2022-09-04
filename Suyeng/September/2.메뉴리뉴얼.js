

//미완성 ㅠㅠ 

function solution(orders, course) {
  let answer = [];
  let stack = [];

  function permute(arr, num) {
    for (let i = 0; i < arr.length; i++) {
      for (const e of permute) {
        arr.slice(); /// order를 돌면서 잘라서 조합해줘야 할 것같은데 이부분은 아직...
      }
    }
  }

  for (const order of orders) {
    for (const e of course) {
      console.log(e);
      console.log(order);

      //order 주문목록으로 주어진 문자열을 쪼개서 재귀를 이용하여 조합해준다.  <-;;; 어렵 ㅠ
      for (const p of permute(order, e)) {
      }
      // 조합한 결과 중 2개 이상 나온 것들을 배열에 저장한다.
      // 배열을 알파벳 오름차순으로 정렬한다.
    }
  }

  return answer;
}

const orders = ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"];
const course = [2, 3, 4];

console.log(solution(orders, course));
