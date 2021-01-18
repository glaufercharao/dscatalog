import React from 'react';
import './styles.scss';
import { ReactComponent as ArrowIcon } from 'core/assets/images/Seta.svg'
import { generateList } from 'core/utils/list';

type Props = {
    totalPages: number;
    activePage: number;
    onChange: (item:number) => void;
}

const Pagination = ({totalPages,activePage, onChange}:Props) => {
    const items = generateList(totalPages);
    return (
        <div className="paginator-container">
            <ArrowIcon className="paginator-previus" />
            {items.map(item => (
                <div 
                    key={item}
                    className={`paginator-item ${item === activePage ?'active':''}`}
                    onClick={() => onChange(item)}>
                    {item+1}
                </div>
            ))}
            <ArrowIcon className="paginator-next" />
        </div>
    );
}
export default Pagination