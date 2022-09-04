function solution(s)
{
    const stack = [];

    s.split('').forEach(_d => {
        if(stack.length === 0){
            // stack is empty
            stack.push(_d);
        }else{
            // stack is not empty
            if(stack[stack.length - 1] === _d){
                stack.pop();
            }else{
                stack.push(_d);
            }
        }
    })

    if(stack.length === 0){
        return 1;
    }

    return 0;
}
/**
 * stack 이용함.
 * array에 하나씩 넣어가며 맨 위에 있는 녀석과 같으면 pop하고 그렇지 않으면 push하여 짝지어 제거 가능한지 확인할수있다.
 */