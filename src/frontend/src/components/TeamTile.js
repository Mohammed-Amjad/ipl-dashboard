import React from 'react';
import { Link } from 'react-router-dom';
import './TeamTile.scss';
import { TeamLogo } from './TeamLogo';

export const TeamTile = ({ teamName }) => {
    return (
        <Link to={`/${teamName}`}>
            <div className={`TeamTile ${teamName.toLocaleLowerCase().split(' ')[0]}`}>
                <h1>{teamName}</h1>
                <TeamLogo teamName={teamName} />
            </div>
        </Link>
    );
}