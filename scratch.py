puzzle = [
    ['J', 'S', 'O', 'L', 'U', 'T', 'I', 'S'],
    ['S', 'U', 'N', 'A', 'R', 'U', 'U', 'A'],
    ['N', 'E', 'P', 'T', 'U', 'N', 'E', 'T'],
    ['S', 'O', 'N', 'I', 'E', 'I', 'S', 'U'],
    ['R', 'C', 'E', 'V', 'T', 'R', 'E', 'R'],
    ['A', 'H', 'T', 'R', 'A', 'E', 'S', 'N'],
    ['M', 'M', 'E', 'R', 'C', 'U', 'R', 'Y']
]

puzzle_rows = len(puzzle)
puzzle_cols = len(puzzle[0])

row = 0
col = 0

directions = 8

# curr_key = "EARTH"
# curr_key = "JUPITER"
# curr_key = "MARS"
# curr_key = "MERCURY"
# curr_key = "NEPTUNE"
# curr_key = "SATURN"
# curr_key = "URANUS"
# curr_key = "VENUS"

keys_arr = [
    "EARTH",
    "JUPITER",
    "MARS",
    "MERCURY",
    "NEPTUNE",
    "SATURN",
    "URANUS"
]

key_constructors = []
all_key_constructors = []

key_stat = [0 for i in range(len(keys_arr))]
key_count = 0

found = False
found_idx = [-1, -1]
count_comparison = 0

def print_keyword(puzzle, key_constructors):
    for row in range (len(puzzle)):
        for col in range(len(puzzle[0])):
            if([row, col] in key_constructors):
                print(puzzle[row][col], end=" ")
            else:
                print("-", end=" ")
        print()

def output_program(puzzle, all_key_constructors, keys_arr):
    for i in range(len(keys_arr)):
        print(keys_arr[i])
        for row in range (len(puzzle)):
            for col in range(len(puzzle[0])):
                if([row, col] in all_key_constructors[i]):
                    print(puzzle[row][col], end=" ")
                else:
                    print("-", end=" ")
            print() 
        print("===")

while(not found) and (key_count < len(keys_arr)):
    curr_key = keys_arr[key_count]

    row = 0
    while (row < puzzle_rows) and (key_stat[key_count] == 0):
        col = 0
        while (col < puzzle_cols) and (key_stat[key_count] == 0):
            # curr_word = puzzle[row][col]    

            dir_state = 0
            key_constructors = []
            while(dir_state < 8) and (key_stat[key_count] == 0):
                if(dir_state == 0):
                    boundary_length = row + 1
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row-finding_idx][col])):
                            key_idx = [row-finding_idx, col]
                            key_constructors.append(key_idx)

                            finding_idx += 1
                        
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 

                elif(dir_state == 1):
                    boundary_length = row + 1
                    x_boundary = puzzle_cols - col 
                    if (x_boundary < boundary_length):
                        boundary_length = x_boundary
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row-finding_idx][col+finding_idx])):
                            key_idx = [row-finding_idx, col+finding_idx]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 
                
                elif(dir_state == 2):
                    boundary_length = puzzle_cols - col 
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row][col+finding_idx])):
                            key_idx = [row, col+finding_idx]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 
                
                elif(dir_state == 3):
                    boundary_length = puzzle_cols - col 
                    y_boundary = puzzle_rows - row
                    if(y_boundary < boundary_length):
                        boundary_length = y_boundary
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row+finding_idx][col+finding_idx])):
                            key_idx = [row+finding_idx, col+finding_idx]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1 
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 
                
                elif(dir_state == 4):
                    boundary_length = puzzle_rows - row
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row+finding_idx][col])):
                            key_idx = [row+finding_idx, col]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 
                
                elif(dir_state == 5):
                    boundary_length = puzzle_rows - row
                    x_boundary = col + 1
                    if(x_boundary < boundary_length):
                        boundary_length = x_boundary
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row+finding_idx][col-finding_idx])):
                            key_idx = [row+finding_idx, col-finding_idx]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 

                elif(dir_state == 6):
                    boundary_length = col + 1
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row][col-finding_idx])):
                            key_idx = [row, col-finding_idx]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 

                else:
                    boundary_length = row + 1
                    x_boundary = col + 1
                    if(x_boundary < boundary_length):
                        boundary_length = x_boundary
                    if(len(curr_key) <= boundary_length):
                        finding_idx = 0
                        while((finding_idx < len(curr_key)) and (curr_key[finding_idx] == puzzle[row-finding_idx][col-finding_idx])):
                            key_idx = [row-finding_idx, col-finding_idx]
                            key_constructors.append(key_idx)
                            
                            finding_idx += 1
                        if (finding_idx == len(curr_key)):
                            key_stat[key_count] = 1
                            found_idx = [row, col]
                            all_key_constructors.append(key_constructors)
                        else:
                            key_constructors.clear() 

                dir_state += 1
            col += 1
        row += 1

    if(0 not in key_stat):
        found = True
    key_count += 1

print("===")
# print(f"Hasil ditemukan: {found}, dengan starting point ({found_idx[0]}, {found_idx[1]})")
print(f"Hasil ditemukan: {found}")
print("===")
# print(f"Penyusun kata: {key_constructors}")
output_program(puzzle, all_key_constructors, keys_arr)
print("===")

# print_keyword(puzzle, key_constructors)
# print("===")

    # for row in range (puzzle_rows):
    #     for col in range (puzzle_cols):
    #         # print(count+1)
    #         # count += 1
    #         curr_word = puzzle[row][col]
