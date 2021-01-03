import axios,{Method} from 'axios';
import { url } from 'inspector';
import { type } from 'os';

type RequestParams ={
    method?: Method,
    url:String,
    data?: Object,
    params?: Object
}
const BASE_URL= 'http://localhost:3000/';

export const makeRequest =({method = 'GET', url, data,params}:RequestParams) => {
    return axios({
        method,
        url:`${BASE_URL}${url}`,
        data,
        params
    });
}
