import React from 'react';
import { Link } from 'react-router-dom';
import './YearsList.scss'

export const YearsList = ({ teamName, firstYear, lastYear }) => {

    const yearList = [];

    for (let year = firstYear; year <= lastYear; year++) {
        yearList.push(year);
    }

    return (
        <div className="YearsList">
            <h3 className="year-heading">Select Year</h3>
            <ol>
                {yearList.map((year, i) => <li key={i} className="year">
                    <Link to={`/${teamName}/matches/${year}`}>
                        <h3>{year}</h3>
                    </Link>
                </li>)}
            </ol>
        </div>
    );

}