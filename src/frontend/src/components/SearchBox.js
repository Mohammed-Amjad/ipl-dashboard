import React from 'react';
import './SearchBox.scss'
export const SearchBox = ({ onSearchChange, placeholder }) => {
    return (
        <div className="SearchBox">
            <input
                className="search-box"
                type='search'
                placeholder={placeholder}
                onChange={onSearchChange} />
        </div>
    );
}