function solution(queue1, queue2) {
  // queue1 = [1]
  // queue2 = [2]
  let initialTotalSum = 0;
  for (let i = 0; i < queue1.length; i++) {
    initialTotalSum += queue1[i] + queue2[i];
  }

  if (initialTotalSum % 2 !== 0) return -1;

  const newArray = [...queue1, ...queue2];

  let i = 0;
  let j = queue1.length;

  let sumOfMovingQueueValue = 0;

  let count = 0;

  const targetSum = initialTotalSum / 2;

  while (i < j) {
    if (sumOfMovingQueueValue === targetSum) {
      return count;
    } else if (sumOfMovingQueueValue < targetSum) {
      sumOfMovingQueueValue += newArray[j];
      j += 1;
    } else {
      sumOfMovingQueueValue -= newArray[i];
      i += 1;
    }
    count += 1;
  }

  return -1;
}
